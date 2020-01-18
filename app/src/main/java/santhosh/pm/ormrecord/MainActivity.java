package santhosh.pm.ormrecord;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;
import santhosh.pm.ormorange.OrmHelper;
import santhosh.pm.ormorange.orangeUtil.OrangeORMDb;


import static santhosh.pm.ormorange.OrangeORMRecord.find;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OrangeOrmContext.init(this);
        OrmHelper.initOrangeORM(this);

        StudentOrm studentOrm = new StudentOrm();
        studentOrm.name = "santhosh P.M";
        studentOrm.marks = "200";
        studentOrm.save();

        List<StudentOrm> studentOrms =  find(StudentOrm.class,"Marks =?", new String[]{String.valueOf(200)});
        for(int i=0; i<studentOrms.size(); i++){
            studentOrm.name = studentOrms.get(i).name;
        }
        Toast.makeText(this, "data is " + studentOrms.get(0).getName(), Toast.LENGTH_SHORT).show();
    }
}
