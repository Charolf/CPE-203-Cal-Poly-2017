import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   // public boolean equals(Object other) { return(Objects.equals(this, other)); }

   public boolean equals(Object other)
   {
      if(other!=null)
      {
      	if (getClass() == other.getClass())
      	{
      		boolean surnameEQ = Objects.equals(surname, ((Student)other).surname);
      		boolean givenNameEQ = Objects.equals(givenName, ((Student)other).givenName);
      		boolean ageEQ = (this.age == ((Student)other).age);
      		boolean currentCoursesEQ = Objects.equals(currentCourses, ((Student)other).currentCourses);
      		return surnameEQ && givenNameEQ	&& ageEQ && currentCoursesEQ;
      	}
      }
      return false;
   }

   public int hashCode() { return(Objects.hash(surname, givenName, age, currentCourses)); }
}
