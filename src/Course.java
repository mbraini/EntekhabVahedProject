public abstract class Course {
    String name;
    String professor;
    String code;
    int size;
    int credit;
    String ClassTime;
    String ExamTime;

    public Course(String name, String professor, String code, int size, int credit, String classTime, String examTime) {
        this.name = name;
        this.professor = professor;
        this.code = code;
        this.size = size;
        this.credit = credit;
        ClassTime = classTime;
        ExamTime = examTime;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCode() {
        return code;
    }

    public int getSize() {
        return size;
    }

    public int getCredit() {
        return credit;
    }

    public String getClassTime() {
        return ClassTime;
    }

    public String getExamTime() {
        return ExamTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setClassTime(String classTime) {
        ClassTime = classTime;
    }

    public void setExamTime(String examTime) {
        ExamTime = examTime;
    }
}
