package github.zhanggang.autoconfigurer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {

//    private Klass class1;
//
//    private Student student100;

    @Override
    public void ding() {
        //System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        System.out.println("hello,my school,ding~~~");
    }

}
