package bootcamp.core.models;



import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Model(adaptables = {Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyDetails {
    @Inject
    private String firstName;

    @Inject
    private String lastName;

    @Inject
    private Date dateOfBirth;

    @Inject
    private String gender;

    @Inject
    private String martialStatus;

    private String fullName;
    private int age;
    private String honorific;

        public int findAge(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int date = cal.get(Calendar.DATE);
        LocalDate original = LocalDate.of(year, month, date);
        LocalDate current = LocalDate.now();
        Period period = Period.between(current, original);
        int diff = period.getYears();
        return diff;

    }

    @PostConstruct
    protected void init(){
        fullName = firstName + " " + lastName;

        if(gender.equals("male")){
            honorific = "Mr";
        }
        else if(gender.equals("female") && martialStatus.equals("single")){
            honorific = "Ms";
        }
        else if(gender.equals("female") && martialStatus.equals("married")){
            honorific = "Mrs";
        }
        age = findAge();
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public String getGender(){
        return gender;
    }

    public String getMartialStatus(){
        return martialStatus;
    }

    public String getFullName(){
        return fullName;
    }
    public int getAge(){
        return age;
    }
    public String getHonorific(){
        return honorific;
    }
}
