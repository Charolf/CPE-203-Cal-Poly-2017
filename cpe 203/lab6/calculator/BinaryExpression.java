public class BinaryExpression extends Expression
{
   private final Expression lft;
   private final Expression rht;
   private final String operator;

   public BinaryExpression(final Expression lft, final Expression rht, String operator)
   {
   	this.lft = lft;
   	this.rht = rht;
   	this.operator = operator;
   }
   
   public String toString()
   {
      return "(" + lft + operator + rht + ")";
   }

   public double evaluate(final Bindings bindings)
   {
      double l = lft.evaluate(bindings);
      double r = rht.evaluate(bindings);
      double result = _applyOperator(l,r);
      return result;
   }

   protected abstract double _applyOperator(double lftt, double rht) {}
}
