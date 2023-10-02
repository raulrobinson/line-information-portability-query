package co.com.telefonica.ws.controller;

import co.com.telefonica.ws.controller.dto.QueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/telefonica/v1/user-id-orc")
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String test() { return "Hello Server"; }

    @PostMapping("/execute-query")
    public ResponseEntity<List<Map<String, Object>>> executeQuery(@RequestBody QueryDTO queryDTO) {
        try {
            log.info("execution={}", queryDTO);
            List<Map<String, Object>> result = jdbcTemplate.queryForList(queryDTO.getSqlQuery());
            log.info("Success exec");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error Query");
            return ResponseEntity.badRequest().body(null);
        }
    }

}
