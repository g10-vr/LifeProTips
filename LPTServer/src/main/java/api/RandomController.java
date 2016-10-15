package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vishal Rathod on 2016-10-05.
 */
@RestController
public class RandomController {
    @RequestMapping("/random")
    public Random randomThread() {
        return new Random("test", "test", "test", "test", "test", 123.0, 123.0, 123.0);
    }
}
