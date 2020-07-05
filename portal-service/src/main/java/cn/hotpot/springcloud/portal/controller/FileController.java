package cn.hotpot.springcloud.portal.controller;

import cn.hotpot.springcloud.portal.fegin.FileClient;
import cn.hotpot.springcloud.portal.model.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qinzhu
 * @since 2020/7/2
 */
@RestController
@RequestMapping("/files")
@Api(tags = "文件")
public class FileController {

    @Autowired
    private FileClient fileClient;

    /**
     * 获取路径下的文件名/文件名
     *
     * @param path 指定路径
     * @return 指定路径下的文件名/文件名
     */
    @GetMapping
    @ApiOperation("展示指定路径下的文件/文件夹")
    public ResponseEntity<Map<String, Object>> listFile(@RequestParam(defaultValue = "/") String path, HttpServletRequest request) {
        UserVO user = (UserVO) request.getSession().getAttribute("user");
        List<String> files = fileClient.listFiles(path);
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        map.put("files", files);
        return ResponseEntity.ok(map);
    }
}
