package k15.k15dcpm03.demo.config;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import k15.k15dcpm03.demo.models.StudentInfo;
import k15.k15dcpm03.demo.models.StudentJPA;

import java.util.Random;

@Component
public class InitDatabase implements ApplicationRunner {
	
	private StudentJPA jpa;

    @Autowired
    public InitDatabase(StudentJPA jpa) {
        this.jpa = jpa;
    }
    
    String[] ho= {"Nguyễn", "Lê", "Trần", "Hồ", "Lều", "Đinh", "Mạc", "Văn", "Ninh", "Dương"};
    String[] ten= {"Anh", "Ngọc An", "Bình", "Quỳnh", "Hoài Ân", "Văn Toản", "Thắng", "Thịnh", "Trường", "Ngân"};
    String[] diachi={"371 Nguyễn Kiệm, P3, Gò Vấp","Lý Thường Kiêt, P.7, Quận 10", "Ninh Thuận", "Bình Thuận","Bình Chánh", "Quận 8","Quận 1, TP. Hồ Chí Minh", "Quận 7, TP.Hồ Chí Minh", "Đức Hòa, Long An","Gò Công Đông, Tiền Giang"};
    
    
    private static int random1()
    {
    	Random rand = new Random();

    	// nextInt as provided by Random is exclusive of the top value so you need to add 1 

    	int randomNum = rand.nextInt((9) + 1);
    	return randomNum;
    }
    
    
   
    
    //public void run(String...args) 
//    public void run(String[] args) 
//    {
//    	
//    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i=0; i<1000; i++)
		{
		jpa.save(new StudentInfo("01002005"+random1(),ho[random1()]+" "+ten[random1()], diachi[random1()], "abc@gmail.com" ));
		}
	}
	

}
