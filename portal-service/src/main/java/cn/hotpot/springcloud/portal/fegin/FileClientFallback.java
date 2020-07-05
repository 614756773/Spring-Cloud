package cn.hotpot.springcloud.portal.fegin;

import java.util.List;

/**
 * @author qinzhu
 * @since 2020/7/2
 */
public class FileClientFallback implements FileClient {
    @Override
    public List<String> listFiles(String path) {
        throw new RuntimeException("该接口触发降级");
    }
}
