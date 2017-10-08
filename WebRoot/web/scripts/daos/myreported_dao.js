/**
 * Created by admin on 2017/4/18.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('myreportedDao', myreportedDao);

    myreportedDao.$inject = ['baseHttp'];

    function myreportedDao(baseHttp) {
        return{
            listTotal:function(page){
                return baseHttp({   //表单
                    method: 'POST',
                    url:'/proxy/area/reports',
                    data: page
                });

            },
            myReport:function(param){  //报备弹出框中的提交
                return baseHttp({
                    method: 'POST',
                    //?rid="+param.rid+"&company="+param.company+"&contact_name="+param.contact_name+"&contact_way="+param.contact_way+"&comment="+param.comment
                    url: "/proxy/area/report",
                    data: param
                });
            },
        }
    }
})();

