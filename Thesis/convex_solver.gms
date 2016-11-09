
Variables f, x1, x2, x3, ru;
Equation f1,f2,f3,f4, func;

f1..
func.. f =e= 100*sqr(x2 - sqr(x1)) + sqr(1 - x1);

x1.lo = -10; x1.up =  5; x1.l = -1.2;
x2.lo = -10; x2.up = 10; x2.l =  1.0;

Model rosenbr / all /;
Solve rosenbr minimizing f1 using nlp;