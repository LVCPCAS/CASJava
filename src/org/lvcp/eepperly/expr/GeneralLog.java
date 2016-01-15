package org.lvcp.eepperly.expr;

/**
 * Created by dboyd16 on 12/16/15.
 */
import java.util.List;
import java.util.ArrayList;
public class GeneralLog extends Expr{
    public GeneralLog(List<Expr> arguments) {
        super(arguments);
    }
    public Expr differentiate(){
        List <Expr> expTerms = new ArrayList<>();
        expTerms.add(arguments.get(0));
        expTerms.add(Expr.MINUS_ONE);
        Expr expTerm= new Exponential(expTerms);
        List <Expr> prodTerms=new ArrayList<>();
        prodTerms.add(arguments.get(0).differentiate());
        prodTerms.add(expTerm);
        prodTerms.add( new NumConstant( 1/Math.log(arguments.get(1).evaluate(0)) ) );
        return (new Product(prodTerms));
    }
    public double evaluate(double value){return (Math.log(arguments.get(0).evaluate(value))/(Math.log(arguments.get(1).evaluate(value))));}
    public String toString(){
        return ("log_"+arguments.get(1).toString()+"("+arguments.get(0).toString()+")");
    }
}
