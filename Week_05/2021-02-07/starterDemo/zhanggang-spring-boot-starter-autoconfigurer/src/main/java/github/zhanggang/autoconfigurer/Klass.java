package github.zhanggang.autoconfigurer;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}
