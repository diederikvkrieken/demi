clear all

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
Z = exp(-X+Y)/exp(1);
normZ = Z - min(Z(:));
normZ = normZ ./ max(normZ(:)); % *
contour3(X,Y,Z,50)
xlabel('water')
ylabel('base')
zlabel('utility')
hold on
%[t1,t2] = meshgrid(x);
%t2 = t2+log(0.4415);
%z = 1.2 * ones(1,21);
%t3 = meshgrid(z);

%surf(t1,t2,t3)
water = 0.3;
base = 0.3;
scatter(water,base);
u = 0.4415;
plot(x, (x+log(u)+1));
scatter(0.5*(-(log(u)+1)+water+base),0.5*((log(u)+1)+water+base))
%bod = [0.7;0.3];
%scatter(bod)
%plot(bod)
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