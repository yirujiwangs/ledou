package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yeran on 2016/10/2.
 */

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
public class BaseService {
}
