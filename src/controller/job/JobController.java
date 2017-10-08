package controller.job;

import com.alibaba.fastjson.JSONObject;
import model.base.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProxyFinanceService;
import service.SupervisorService;
import utils.beanstalkd.BeanstalkClient;
import utils.beanstalkd.BeanstalkJob;
import utils.beanstalkd.BeanstalkUtil;
import utils.common.JobConstatnt;
import utils.common.LogUtil;

/**
 * Created by yeran on 2017/5/9.
 */

@RestController
@RequestMapping("/job")
public class JobController {

    public static final String SECRET = "YERAN";

    public static boolean PROXY_FINANCE_SETTLE = true;

    @Autowired
    private SupervisorService supervisorService;


    @RequestMapping("/proxyFinanceSettle")
    public BaseResult proxyFinanceSettleJob(@RequestParam("type") String type,@RequestParam("secret") String secret){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"启动清算线程失败");
        if(type.equals("start") && SECRET.equals(secret)){
            //执行多线程处理
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (PROXY_FINANCE_SETTLE){
                        String tube = JobConstatnt.TUBE_PROXY_SETTLE_FINANCE;
                        BeanstalkClient client = new BeanstalkClient(BeanstalkUtil.BEANSTALK_SERVICE_IP,
                                BeanstalkUtil.BEANSTALK_SERVICE_PORT, tube);
                        //System.out.println("监听" + tube + "...");
                        BeanstalkJob job;
                        try {
                            while ((job = client.reserve(null)) != null) {
                                byte[] bs = job.getData();
                                String str = new String(bs);
                                JSONObject jsonObject = JSONObject.parseObject(str);

                                //System.out.println(jsonObject.toJSONString());

                                String utoken = jsonObject.getString("utoken");
                                Long stime = jsonObject.getLong("stime");
                                Long etime = jsonObject.getLong("etime");
                                Integer id = jsonObject.getInteger("id");

                                boolean handleResult = supervisorService.handleSettleProxyIncome(id,utoken,stime,etime);

                                if (handleResult){
                                    client.deleteJob(job);
                                }else {
                                    //System.out.println("任务处理失败");
                                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "资金清算任务失败");
                                    client.deleteJob(job);
                                }
                            }
                        }catch (Exception e){
                            LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "资金清算任务失败"+e.getMessage());
                        }
                    }
                }
            }).start();

            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("启动清算线程成功");
            return baseResult;
        }

        return baseResult;
    }
}
