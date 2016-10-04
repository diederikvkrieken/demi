clear all

x = [0:.05:1];
%y = [0:.7:300];
alpha = 0.3;
[X1,X2] = meshgrid(x, x);
for i =1:length(X1)
    for j=1:length(X1(1,:))
        Y1(i,j)=0;
        Y1(i,j) = (i^(alpha)) / (j^(alpha));
        if (Y1(i,j)<0.7)
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
x = [0:.05:1];
[X1,X2] = meshgrid(x, x);
for i =1:length(X1)
    for j=1:length(X1(1,:))
        Y1(i,j)=0;
        Y1(i,j) = 0.5*log(i) + 0.5*log(j);
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
        Y1(i,j) = 0.3*log(i) - 0.4*log(j);
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