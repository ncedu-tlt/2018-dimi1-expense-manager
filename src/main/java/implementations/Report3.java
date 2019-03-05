package implementations;

import interfaces.PlanBudget;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Report3 {
    private BigInteger id;
    private Date date;
    private BigDecimal sum;
    private String description;

    private String minutes;
    private String hours;
    private String dayMonth;
    private String months;
    private String dayWeek;
    private String years;

    private Integer cntMin;
    private Integer cntHour;
    private Integer cntDM;
    private Integer cntM;
    private Integer cntDW;
    private Integer cntY;

    private JdbcTemplate jdbcTemplate;

    public Report3(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public void getReportRow(List<PlanBudget> plans, List<Report3> rL, Date criterialStartDate, Date criterialEndDate) {
        for(int i=0; i<plans.size(); i++){
           if(plans.get(i).getOperationDate() != null && plans.get(i).getOperationDate().after(criterialStartDate) &&
                   plans.get(i).getOperationDate().before(criterialEndDate)){
               System.out.println(plans.get(i).getOperationDate());
               Report3 obj = new Report3(jdbcTemplate);
               obj.setId(BigInteger.valueOf(plans.get(i).getBudgetTypeId()));
               obj.setDate(plans.get(i).getOperationDate());
               obj.setDescription(plans.get(i).getDescription());
               obj.setSum(plans.get(i).getChargeValue());
               rL.add(obj);
           } else if(plans.get(i).getStartDate() != null && (plans.get(i).getStartDate().after(criterialStartDate) ||
                   plans.get(i).getStartDate().equals(criterialStartDate))){
               if(plans.get(i).getRegularMask() != null){
                   String regM = plans.get(i).getRegularMask();
                   if(regM != null){
                       parseRegularMask(regM);
                       countMask();
                       if((!minutes.equals("*") || !hours.equals("*")) && dayMonth.equals("*") && months.equals("*") &&
                               dayWeek.equals("*") && years.equals("*")){
                           if(plans.get(i).getRepeatCount() != null){
                               int counter = 1;
                               Calendar instance = Calendar.getInstance();
                               instance.setTime(plans.get(i).getStartDate());
                               int incr = 1;
                               if(cntMin>1 && cntHour>1){
                                   incr = cntMin*cntHour;
                               } else if(cntMin>1 && cntHour==1){
                                   incr = cntMin;
                               } else if(cntHour>1 && cntMin==1){
                                   incr = cntHour;
                               }
                               while(counter<=plans.get(i).getRepeatCount()*incr){
                                   Report3 obj = new Report3(jdbcTemplate);
                                   obj.setId(BigInteger.valueOf(plans.get(i).getBudgetTypeId()));
                                   instance.add(Calendar.DAY_OF_MONTH, 1);
                                   Date currentDate = instance.getTime();
                                   if(plans.get(i).getEndDate() != null && currentDate.after(criterialEndDate)){
                                       break;
                                   }
                                   obj.setDate(currentDate);
                                   obj.setDescription(plans.get(i).getDescription());
                                   obj.setSum(plans.get(i).getChargeValue());
                                   rL.add(obj);
                                   counter++;
                               }
                           } else if(plans.get(i).getRepeatCount() == null) {
                               if (plans.get(i).getEndDate() != null) {
                                   Calendar instance = Calendar.getInstance();
                                   instance.setTime(plans.get(i).getStartDate());
                                   Date currentDate = instance.getTime();
                                   while (currentDate.before(plans.get(i).getEndDate())) {
                                       Report3 obj = new Report3(jdbcTemplate);
                                       obj.setId(BigInteger.valueOf(plans.get(i).getBudgetTypeId()));
                                       instance.add(Calendar.DAY_OF_MONTH, 1);
                                       currentDate = instance.getTime();
                                       obj.setDate(currentDate);
                                       obj.setDescription(plans.get(i).getDescription());
                                       obj.setSum(plans.get(i).getChargeValue());
                                       rL.add(obj);
                                   }
                               }
                           }
                       }
                   }
               }
           }
        }
    }

    public void parseRegularMask(String regM){
        String[] mask = regM.split(" ");
        if(mask[0] != "*") {
            minutes = mask[0];
        }
        if(mask[1] != "*") {
            hours = mask[1];
        }
        if(mask[2] != "*") {
            dayMonth = mask[2];
        }
        if(mask[3] != "*") {
            months = mask[3];
        }
        if(mask[4] != "*") {
            dayWeek = mask[4];
        }
        if(mask[5] != "*") {
            years = mask[5];
        }
        System.out.println(minutes+" "+hours+" "+dayMonth+" "+months+" "+dayWeek+" "+years);
    }

    public void countMask(){
        cntMin=1; cntHour=1; cntDM=1; cntM=1; cntDW=1; cntY=1;
        Pattern pattern = Pattern.compile("(,)");
        Matcher matchMin = pattern.matcher(minutes);
        while(matchMin.find()){
            cntMin++;
        }
        Matcher matchHour = pattern.matcher(hours);
        while(matchHour.find()){
            cntHour++;
        }
        Matcher matchDM = pattern.matcher(dayMonth);
        while(matchDM.find()){
            cntDM++;
        }
        Matcher matchMonth = pattern.matcher(months);
        while(matchMonth.find()){
            cntM++;
        }
        Matcher matchDW = pattern.matcher(dayWeek);
        while(matchDW.find()){
            cntDW++;
        }
        Matcher matchY = pattern.matcher(years);
        while(matchY.find()){
            cntY++;
        }
    }

    public void getReportRow(List<Report3> rL) {
        String getData = "SELECT budget_type_id_fk, description, summa FROM plan_budget";
        RowMapper<Report3> rowMapper = new Report3RowMapper(jdbcTemplate);
        List<Report3> getRows = jdbcTemplate.query(getData, rowMapper);
        for(Report3 i : getRows){
            rL.add(i);
        }
    }

    public BigInteger getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getSum() { return sum; }

    public Date getDate() { return date;}

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public  void setDate(Date date) { this.date = date; }
}