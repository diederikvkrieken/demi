
Variables nash, x1, x2, x3, ru, u1, u2, u3, u4;
Equation f1,f2,f3,f4, func,u11, u22, u33, u44;

*x1 = acid, x2=base, x3 = water.
*anion
f1.. u1 =e= exp(x2-x3)/exp(1);
*cation
f2.. u2 =e= exp(x1-x3)/exp(1);
*mixbed
f3.. u3 =e= exp(x1+x2+(x3))/exp(3);
*neut
f4.. u4 =e= exp(-x1-x2);
*max function
func.. nash =e= u1*u2*u3*u4;
Equation func2, func3;
ru.lo = 0.0; ru.up = 0.6;
func2.. nash =g= 0;
func3.. ru =e= ru;

u11.. u1 =G= ru;
u22.. u2 =G= ru;
u33.. u3 =G= ru;
u44.. u4 =G= ru;


x1.lo = 0; x1.up =  1;
*x1.l = -1.2;
x2.lo = 0; x2.up = 1;
*x2.l =  1.0;
x3.lo = 0; x3.up = 1;
*x3.l =  1.0;

*ru.lo = 0.286; ru.up = 0.286;


Model nashcalc / all /;
Solve nashcalc maximize ru using nlp;

Display x1.l, x2.l, x3.l,ru.l, nash.l;




*Variables f, x1, x2;
*Equation func;

*func.. f =e= 100*sqr(x2 - sqr(x1)) + sqr(1 - x1);

*x1.lo = -10; x1.up =  5; x1.l = -1.2;
*x2.lo = -10; x2.up = 10; x2.l =  1.0;

*Model rosenbr / all /;
*Solve rosenbr minimizing f using nlp;
