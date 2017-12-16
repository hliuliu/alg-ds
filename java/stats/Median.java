public class Median {
	public static void main(String[]args) {
		System.out.println(median(new double[]{4,6,9},new double[]{1,3,5,8}));
		Double x=new Double(3),y=new Double(4);
		swap(x,y);
		System.out.println(x+" "+y);
	}

	public static void swap(Double a,Double b) {
		double temp=a.value;
		a.value=b.value;
		b.value=temp;
	}

	public static double median(double a,double b,double c) {
		double temp;
		if(a>b) {
			temp=a;
			a=b;
			b=temp;
		}
		if(a>c) {
			temp=a;
			a=c;
			c=temp;
		}
		return b<c? b:c;
	}

	public static double median(double a,double b,double c,double d) {
		double temp;
		if(a>b) {
			temp=a;
			a=b;
			b=temp;
		}
		if(a>c) {
			temp=a;
			a=c;
			c=temp;
		}
		if(a>d) {
			temp=a;
			a=d;
			d=temp;
		}
		if(d<b) {
			temp=b;
			b=d;
			d=temp;
		}
		if(d<c) {
			temp=c;
			c=d;
			d=temp;
		}
		return (b+c)/2;
	}

	public static double[] reduce(double[]a,int start,int end) {
		double[]b=new double[end-start+1];
		for(int i=0;i<b.length;i++) {
			b[i]=a[start+i];
		}
		return b;
	}

	public static double[] concat(double[]a,double[]b) {
		double[]c=new double[a.length+b.length];
		for(int i=0;i<c.length;i++) {
			double val= i<a.length? a[i]:b[i-a.length];
			c[i]=val;
		}
		return c;
	}

	public static void mergeSort(double[]a) {
		if(a.length<2) return;
		double[]a1=reduce(a,0,a.length/2-1);
		double[]a2=reduce(a,a.length/2,a.length-1);
		mergeSort(a1);
		mergeSort(a2);
		int ai=0,a1i=0,a2i=0;
		while(a1i<a1.length && a2i<a2.length) {
			if(a1i==a1.length) a[ai++]=a2[a2i++];
			else if(a2i==a2.length) a[ai++]=a1[a1i++];
			else a[ai++]= a2[a2i]<a1[a1i]? a2[a2i++]: a1[a1i++];
		}
	}

	public static double median(double[]a) {
		if (a.length==0) {
			throw new RuntimeException("Cannot calculate median of an empty array.")
		}
		return a.length%2==1? a[a.length/2]: (a[a.length/2]+a[a.length/2-1])/2;
	}

	public static double median(double[]a1,double[]a2) {
		// a1, a2 must be sorted
		if(a1.length>a2.length) return median(a2,a1);
		if(a1.length==0) return median(a2);
		int a1m=(a1.length-1)/2;
		int a2m=(a2.length-1)/2;
		if(a1.length==1) {
			if(a2.length==1) return (a1[0]+a2[0])/2;
			return a2.length%2==0? median(a1[0],a2[a2m],a2[a2m+1]):median(a1[0],a2[a2m-1],a2[a2m],a2[a2m+1]);
		}
		if(a1.length==2) {
			if(a2.length==2) return median(a1[0],a1[1],a2[0],a2[1]);
			return a2.length%2==1? median(a2[a2m],Math.min(a2[a2m+1],a1[0]),Math.max(a2[a2m-1],a1[1])):median(a2[a2m],a2[a2m+1],Math.min(a2[a2m+2],a1[0]),Math.max(a2[a2m-1],a1[1]));
		}
		if(a1[a1m]==a2[a2m]) return a1[a1m];
		if(a1[a1m]>a2[a2m]) return median(reduce(a1,0,a1.length-a1m-1),reduce(a2,a1m,a2.length-1));
		return median(reduce(a2,0,a2.length-a1m-1),reduce(a1,a1m,a1.length-1));
	}
}

class Double {
	double value;
	public Double(double x) {value=x;}
	public String toString() {return value+"";}
}