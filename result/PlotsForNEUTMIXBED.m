%%
%MIXBED
clear all
hold off
x = 0:0.05:1;
u=0.1;
[X,Y] = meshgrid(x);
Z = -X-Y +(log(u)) +3;
surf(X,Y,Z)
x = 0.7;
y = 0.9;
z = 0.2;
hold on
T = X+Y;
%surf(X,Y,T)
scatter3(x,y,z)
a = x + ((log(u)+3-(x+y+z))/(3));
b = y + ((log(u)+3-(x+y+z))/(3));
c = z + ((log(u)+3-(x+y+z))/(3));
scatter3(a,b,c)
xlim([0 1])
ylim([0 1])
zlim([-0.5 1.2])
acid=0.8148148148148149;
base=1.3703703703703705;
water=0.8148148148148149;
scatter3(acid, base, water)
xlabel('x')
ylabel('y')
zlabel('z')


%%
%NEUT
clear all
hold off
x = 0:0.05:1;
[X,Y] = meshgrid(x);
Z = exp(-X-Y);
surf(X,Y,Z);%,50)
xlabel('acid')
ylabel('base')
%zlabel('utility')
hold on

acid = 0.5;
base = 0.25;
%scatter(acid,base);
u = 0.4;
ylim([0 1])
xlim([0 1])

plot(x,-x-((log(u))));
plot(x, x+.2);
plot(x,x-.2);
%a = 0.5*(-(log(u))+acid-base)
%b = 0.5*(-(log(u))-acid+base)
%0 = -x -y -log(u)
%[poina, poinb ]= puntNaarpunt(-1,-1,-log(u),acid,base)
%points = [acid, a;base, b];
%scatter(points(1,:), points(2,:))


if exp(-acid-base) < u
    [a, b]= puntNaarpunt(-1,-1,-log(u),acid,base);
else
    a = acid; b = base;
end
if abs(a)-abs(b) < 0.2
    [na, nb] = puntNaarpunt(1,-1,0.2,a,b);
    points = [acid, a, na;base, b, nb];
    scatter(points(1,:), points(2,:))
    scatter(na, nb)
elseif abs(a)-abs(b) > 0.2
    [na, nb] = puntNaarpunt(1,-1,-0.2,a,b);
    points = [acid, a, na;base, b, nb];
    scatter(points(1,:), points(2,:))
    scatter(na, nb)
else
    points = [acid, a;base, b];
    scatter(points(1,:), points(2,:))
end
hold off
%%
% %clear all
% u = 0.99;
% z = 0.5;
% t = 0.2;
% Q1 = [z; z+(log(u)+1)];
% Q2 = [t; t+(log(u)+1)];
% P = [0.25; 0.5];
% a = 0.05;
% b = 1.04;
% X3 = [0.25,0.5;a,b];
% d2 = pdist(X3,'euclidean')
% sqrt((0.25-a)^2+(0.5-b)^2)
% % Ax+By+X = 0; (m,n)
% % d = Am+Bn+C / sqrt(A^2 + B^2)
% d3 = (0.25-0.5+(log(u)+1))/(sqrt(2))
%%
%ANION
clear all
hold off
x = -.2:0.05:1.2;
[X,Y] = meshgrid(x);
Z = exp(-X+Y)/exp(1);
%Z = 0.5 * (-abs(X).^2+abs(Y).^2);
%normZ = Z - min(Z(:));
%normZ = normZ ./ max(normZ(:)); % *
%contour3(X,Y,Z,50)
%surf(X,Y,Z)
xlabel('water')
ylabel('base')
%zlabel('utility')
hold on

water = 0.9;
base = 0.3;
%scatter(water,base);
u = 0.6;
%ylim([-.1 1.2])
%xlim([-.1 1.2])

y = x +log(u)+1;
plot(x,y)
% = x-y + ln(u)+1;

if exp(-water+base) < u
    [a, b]= puntNaarpunt(1,-1,log(u)+1,water,base);
else
    a = water; b = base;
end

if a<0
    [na, nb] = puntNaarpunt(-1,-1,log(u)+1, a,b);
    plot(x,-x+log(u)+1)
    if b<0
        [na,nb]= puntNaarpunt(-1,-1,-log(u)-1,a,b);
        plot(x,-x-log(u)-1)
    end
    %scatter(na,nb)
elseif b>1
    
    [na, nb] = puntNaarpunt(-1, -1,-log(u)+1, a, b); 
    plot(x,-x-log(u)+1);
    if a>1
    %lijn2 = plot(x,-x-log(u)+1);
        [na,nb] = puntNaarpunt(-1,-1,log(u)+3,a,b);
        plot(x,-x+log(u)+3);
    %lijn4 = plot(x,-x+log(u)+3);
    end
    %scatter(na,nb)
else
    na = a;
    nb = b;
end
%legend([lijn1, lijn2, lijn3, lijn4], '1','2','3','4')
points = [water, a, na;base, b, nb]
scatter(points(1,:), points(2,:))
hold off
%%
clear all
x = 0:0.005:1;
[X,Y,Z] = meshgrid(x);
U = exp(X+Y+2*Z)./exp(4);
max(max(U(:,:)))