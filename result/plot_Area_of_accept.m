x = 0:0.05:1;
[A,B,W] = meshgrid(x);

% u_anion = exp(-W+B)/exp(1);
% u_cation = exp(-W+A)/exp(1);
% u_neut = exp(-A-B)/exp(2);
% u_mixbed = exp(A+B+W)/exp(3);
% test = exp(A+B)/exp(2);
% surf(A,B,test)

%x=water
x = 0:0.05:1;
y = 0:0.05:1;
u = 0.4;

%y = base
y_m2 = plot3(x, (-x-log(u)), zeros(length(x))); %Mixbed
hold on
y_a = plot3(x, (x-(log(u)+1)), zeros(length(x))); % Anion

%z = acid
z_m =  plot3(x, zeros(length(x)), (-x-log(u))); %Mixbed
z_c = plot3(x, zeros(length(x)), (x-(log(u)+1))); % Cation

%x = base, y = acid
z_n1 = plot3(zeros(length(x)), y, (y+0.2)); %Neut
z_n2 = plot3(zeros(length(x)), y, (y-0.2)); %Neut
z_m = plot3(zeros(length(x)),y,-y-log(u)); %Mixbed

%%
 z_m =  surf(x, y, ones(length(x),1)*(-x-log(u))); %Mixbed
hold on
 z_c = surf(x, y, ones(length(x),1)*(x-(log(u)+1))); % Cation

 z_n1 = surf(x, y, ones(length(y),1)*(y+0.2)); %Neut
z_n2 = surf(x, y, ones(length(y),1)*(y-0.2)); %Neut
z_m = surf(x ,y,ones(length(y),1)*-y-log(u)); %Mixbed


xlim([0 1])
ylim([0 1])
zlim([0 1])


%%
%Water and base

%x = water, y = base 

x = 0:0.05:1;
u = 0.7;

a = area(x, (x-(log(u)+1))); % Anion
hold on

m = area(x, (-x-log(u))); %Mixbed


set(gca,'Color',[0.8 0.8 0.8]);
a.FaceColor = [1 1 1];
m.FaceColor = [1 1 1];
m_line = plot(x, (-x-log(u))); %Mixbed
a_line = plot(x, (x-(log(u)+1))); % Anion
l = legend([m_line, a_line],'Mixbed','Anion');
set(l,'color','w')

xlabel('water'),ylabel('base');
 ylim([0 1])
 xlim([0 1])

%for completeness
y = 1:0.05:1.4;
X=1;
%f = area(y, (1.4)*X); %Mixbed
%f.FaceColor = [1 1 1];
%%
scatter(mixbed(:,3),mixbed(:,2));
scatter(anion(:,3),anion(:,2));
%%
%Water and base

x = 0:0.05:1;
u = 0.3;

a = area(x, (x-(log(u)+1))); % Anion
hold on

m = area(x, (-x-log(u))); %Mixbed
set(gca,'Color',[0.99 0.91 0.79]);
%99.22% red, 91.76% green and 79.61% blue,
a.FaceColor = [1 1 1];
m.FaceColor = [1 1 1];
m_line = plot(x, (-x-log(u))); %Mixbed
c_line = plot(x, (x-(log(u)+1))); % Anion
l = legend([m_line, c_line],'Mixbed','Cation');
set(l,'color','w')

xlabel('water'),ylabel('acid');
% ylim([0 1])
% xlim([0 1])

scatter(mixbed(:,2),mixbed(:,3));
scatter(cation(:,3),cation(:,2));
%%
%Base & Acid
x = 0:0.05:1;
u = 0.3;

n1 = area(x, (x+0.2)); % Neut1
hold on
n2 = area(x, (x-0.2)); %Neut2

% x = base
% y = acid
%Neut: ln(u) = -X - Y
n3 = area(x,-x - log(u));

m = area(x,-x-log(u));

set(gca,'Color',[1 1 1 ]);
%99.22% red, 91.76% green and 79.61% blue
n1.FaceColor = [0.99 0.91 0.79];
n2.FaceColor = [1 1 1];
m.FaceColor = [1 1 1];

n1_line = plot(x, (x+0.2)); %Neut
n2_line = plot(x, (x-0.2)); %Neut
n3_line = plot(x, -x-log(u));
m_line = plot(x,-x-log(u)); %Mixbed

l = legend([n1_line, n2_line,n3_line, m_line],'Neut_1','Neut_2', 'Neut_3', 'Mixbed');
set(l,'color','w')

xlabel('base'),ylabel('acid');
% ylim([0 1])
% xlim([0 1])

%%
s_n = scatter(neut(:,2),neut(:,1));
s_m = scatter(mixbed(:,2),mixbed(:,1));
leg = legend([s_n, s_m],'scatter neut','scatter mixbed');

