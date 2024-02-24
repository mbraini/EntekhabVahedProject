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
}
