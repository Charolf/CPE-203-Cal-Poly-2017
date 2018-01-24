import java.time.LocalTime;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // additional likely methods not defined since they are not needed for testing

   public int hashCode()
   {
      //hash makes int key. everything set by default to zero if empty. If not empty, it gets hashed and added to result key (some # int).
      int prefixSH = 0;
      int numberSH = 0;
      int enrollmentSH = 0;
      int startTimeSH = 0;
      int endTimeSH = 0;

      if(prefix != null)
         prefixSH = prefix.hashCode();
      if(number != null)
         numberSH = number.hashCode();
      if(((Integer)enrollment) != null)
         enrollmentSH = ((Integer)enrollment).hashCode();
      if(startTime != null)
         startTimeSH = startTime.hashCode();
      if(endTime != null)
         endTimeSH = endTime.hashCode();

      int result = prefixSH + numberSH + enrollmentSH + startTimeSH + endTimeSH;
      return result;
   }

	public boolean equals(Object other) 
	{
		if (other != null) 
		{
         if (getClass() == other.getClass()) 
         {
            String otherPrefix = ((CourseSection) other).prefix;
            String otherNumber = ((CourseSection) other).number;
            LocalTime otherStart = ((CourseSection) other).startTime;
            LocalTime otherEnd = ((CourseSection) other).endTime;
            int otherEnroll = ((CourseSection) other).enrollment;

            boolean prefixEq, numberEq, enrollmentEq, startTimeEq, endTimeEq;

            if (prefix == null)
               prefixEq = (otherPrefix == null);
            else { prefixEq = (prefix.equals(otherPrefix)); }

            if (number == null)
               numberEq = (otherNumber == null);
            else { numberEq = (number.equals(otherNumber)); }

            if (startTime == null)
               startTimeEq = (otherStart == null);
            else { startTimeEq = (startTime.equals(otherStart)); }

            if (endTime == null)
               endTimeEq = (otherEnd == null);
            else { endTimeEq = (endTime.equals(otherEnd)); }

            enrollmentEq = (enrollment == otherEnroll);

            return prefixEq && numberEq && enrollmentEq && startTimeEq && endTimeEq;
         }
      }
      return false;
   }
}
