package cn.hotpot.springcloud.resource.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;

/**
 * @author qinzhu
 * @since 2020/7/2
 */
@RestController
@RequestMapping("/files")
@Slf4j
@Api(tags = "文件")
public class FileController {

    /**
     * 获取路径下的文件名/文件名
     *
     * @param path 指定路径
     * @return 指定路径下的文件名/文件名
     */
    @GetMapping
    @ApiOperation("获取指定路径下的文件/文件夹")
    public List<String> listFile(@RequestParam(defaultValue = "/") String path) throws InterruptedException {
        // 模拟服务超时，触发降级
        // 百分之五十的概率触发
        if (new Random().nextInt(10) > 5) {
            log.info("模拟服务超时....................");
            Thread.sleep(100 * 1000);
        }

        File file = new File(path);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        if (file.isDirectory()) {
            return Arrays.asList(Objects.requireNonNull(file.list()));
        }

        return Collections.singletonList(file.getName());
    }
}
