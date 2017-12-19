public class Regression {
  private OrderedPair[] reg;
  private int total;
  private Stats s;
  
  public Regression() {
    reg=new OrderedPair[1000];
    total=0;
    s=new Stats();
  }
  
  public void insert(double a,double b) {
    OrderedPair coord=new OrderedPair(a,b);
    reg[total++]=coord;
  }
  
  public void delete() {
    total--;
  }
  
  public void empty() {
    total=0;
  }
  
  public boolean isEmpty() {
    return total==0;
  }
  
  public double meanX() {
    for(int i=0;i<total;i++) {
      s.insert(reg[i].x);
    }
    double val=s.mean();
    s.makeEmpty();
    return val;
  }
  
  public double meanY() {
    for(int i=0;i<total;i++) {
      s.insert(reg[i].y);
    }
    double val=s.mean();
    s.makeEmpty();
    return val;
  }
  
  public double meanXX() {
    for(int i=0;i<total;i++) {
      s.insert(reg[i].x*reg[i].x);
    }
    double val=s.mean();
    s.makeEmpty();
    return val;
  }
  
  public double meanXY() {
    for(int i=0;i<total;i++) {
      s.insert(reg[i].x*reg[i].y);
    }
    double val=s.mean();
    s.makeEmpty();
    return val;
  }
  
  public double slope() {
    return (meanXY()-meanX()*meanY())/(meanXX()-meanX()*meanX());
  }
  
  public double yIntercept() {
    return meanY()-slope()*meanX();
  }
  
  public String toString() {
    String res="{";
    for(int i=0;i<total;i++) {
      res+=reg[i];
      if(i<total-1) res+=",";
    }
    res+="}";
    if(total<2) return res+"\nInsufficient data!";
    res+="\nBest fit line: y="+slope()+"x";
    if(yIntercept()>0)res+="+"+yIntercept();
    if(yIntercept()<0)res+="-"+(-yIntercept());
    return res;
  }
  
}