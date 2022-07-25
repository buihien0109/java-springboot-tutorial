package com.example.coursebackend.service;

import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.Course;
import com.example.coursebackend.model.Supporter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupporterService {
    private List<Supporter> supporters;

    public SupporterService() {
        init();
    }

    public void init() {
        supporters = new ArrayList<>();
        supporters.add(new Supporter(1, "Phạm Thị Mẫn", "man@gmail.com", "0988888888", "https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0"));
        supporters.add(new Supporter(2, "Nguyễn Đức Thịnh", "thinh@gmail.com", "0977777777", "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC"));
        supporters.add(new Supporter(3, "Nguyễn Thanh Hương", "huong@gmail.com", "0966666666", "https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag"));
    }

    public List<Supporter> getSupporters() {
        return supporters;
    }

    public Supporter findById(int id) {
        Optional<Supporter> supporterOptional =  supporters
                .stream()
                .filter(course -> course.getId() == id)
                .findFirst();

        if(supporterOptional.isPresent()) {
            return supporterOptional.get();
        }

        throw new NotFoundException("Không tìm thấy nhân viên tuyển sinh có id = " + id);
    }
}
