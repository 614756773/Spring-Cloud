package cn.hotpot.springcloud.portal.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author qinzhu
 * @since 2020/7/2
 */
@FeignClient(value = "resource-service", fallback = FileClientFallback.class)
public interface FileClient {

    /**
     * 获取文件
     *
     * @param path 指定路径
     * @return 指定路径下的文件/文件夹名称
     */
    @RequestMapping(value = "/resource/files", method = RequestMethod.GET)
    List<String> listFiles(@RequestParam String path);
}
