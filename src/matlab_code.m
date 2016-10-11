clear all
hold off
%x = -10:0.5:10;
x = 0:0.05:1;
[X,Y] = meshgrid(x);
%Z = -(Y.^2)+(X*-Y) ;
%Z = exp((X+Y) - (X-Y).^2);
%Z = (abs(X)./abs(Y)) - ((X-Y).^3);
Z = exp((-X.*Y) - ((X-Y).^2)+1)/exp(1);
W = -(X.^2) + (X.*Y) - (Y.^2) +1;
u = 0.9;
%y = 0.5 * (x + sqrt((-4*u) - (3* (x.^2)) + 4));
%z = 0.5 * (x - sqrt((-4*u) - (3* (x.^2)) + 4));
%contour3(X,Y,Z,30)
%hold on
surf(X,Y,Z)
hold on
ylim([0,1])
%plot(x,y)
%plot(x,z)
xlabel('x')
ylabel('y')
zlabel('z')


%x^2+2*x*y+y^2=0;

%%
x = [0:1:100];
%y = [0:.7:300];
alpha = 0.3;
[X1,X2] = meshgrid(x, x);
for i =1:length(X1)
    for j=1:length(X1(1,:))
        Y1(i,j)=0;
        Y1(i,j) = i/j;
        if (Y1(i,j)<5)
            Y2(i,j) = 1;
        else
            Y2(i,j)= 0;
        end
    end

end

%surf(X1, X2, Y1)
%surf(X1,X2,Y2)
%%
x = 0:0.025:1;
[X,Y] = meshgrid(x);
Z = Y.*exp(-(X+0.3).^2-((Y).^2));
contour3(X,Y,Z,30)
xlabel('water')
ylabel('base')
zlabel('utility')
%%
hold off
clear all
x = 0:0.05:1;
[X,Y] = meshgrid(x);
Z = exp(-X-Y);%/exp(1);
W = exp(X+X)/exp(1);
normZ = Z - min(Z(:));
normZ = normZ ./ max(normZ(:)); % *
surf(X,Y,Z)
%contour3(X,Y,Z,50)

xlabel('acid')
ylabel('base')
zlabel('utility')
hold on
%[t1,t2] = meshgrid(x);
%t2 = t2+log(0.4415);
%z = 1.2 * ones(1,21);
%t3 = meshgrid(z);

%surf(t1,t2,t3)
acid = 0.3;
base = 0.3;
scatter(acid,base);
u = 0.4;
ylim([0 1])
plot(x, (-x-log(u)));
a = 0.5*(-(log(u))+acid-base)
b = 0.5*(-(log(u))-acid+base)
scatter(a,b)
%bod = [0.7;0.3];
%scatter(bod)
%plot(bod)
%%
%%
clear all
hold off
x = 1:10;
y = 11:20;
z = x'*y;
c = randi(10,10);
sliceomatic(x,y,z,c)
%%
clear all
x = 0:0.05:1;
[X,Y,Z] = meshgrid(x, x, x);
c = exp(X+Y+Z);
sliceomatic1p0(c)
%%
clear all
[x,y,z] = meshgrid(-2:.2:2, -2:.25:2, -2:.16:2);
v = x .* exp(-x.^2 - y.^2 - z.^2);
sliceomatic(v)
%%
x = 0:0.05:1;
[X,Y] = meshgrid(x);
Z = 0.5 * (-abs(X).^2+abs(Y).^2);
normZ = Z - min(Z(:));
normZ = normZ ./ max(normZ(:)); % *
contour3(X,Y,Z,50)
xlabel('water')
ylabel('base')
zlabel('utility')

%%
clear all
x = [0:.05:1];
[X1,X2] = meshgrid(x, x);
for i =1:length(X1)
    for j=1:length(X1(1,:))
        Y1(i,j)=0;
        Y1(i,j) = 0.1*log(i) /( 0.1*log(j));
        if (Y1(i,j)<1)
            Y2(i,j) = 1;
        else
            Y2(i,j)= 0;
        end
    end

end

surf(X1, X2, Y1)
%surf(X1,X2,Y2)
xlabel('x_1 or i')
ylabel('x_2 or j')
zlabel('utility')


%%
clear all
x = [-1:0.1:5];
plot(x, (0.5*log(x)) + 0.5*log(10));
%%
clear all
hold off
x = [0:0.05:1];
%y = x.^2;
plot(x,x-0.2)
hold on
plot(x, x+0.2);
ylim([0 1]);
%%

%%
clear all
x = [0:.05:1];
[X1,X2] = meshgrid(x, x);
for i =1:length(X1)
    for j=1:length(X1(1,:))
        Y1(i,j)=0;
        Y1(i,j) = 0.3*i - 0.4*j;
        if (Y1(i,j)<1)
            Y2(i,j) = 1;
        else
            Y2(i,j)= 0;
        end
    end

end

surf(X1, X2, Y1)
%surf(X1,X2,Y2)
xlabel('x_1 or i')
ylabel('x_2 or j')
zlabel('utility')


%%