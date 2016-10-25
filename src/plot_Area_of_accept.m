x = 0:0.05:1;
[A,B,W] = meshgrid(x);

u_anion = exp(-W+B)/exp(1);
u_cation = exp(-W+A)/exp(1);
u_neut = exp(-A-B)/exp(2);
u_mixbed = exp(A+B+W)/exp(3);
test = exp(A+B)/exp(2);
surf(A,B,test)

%%
%Water and base

x = 0:0.05:1;
u = 0.3;

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
ylim([0 1])
xlim([0 1])

%%
%Base & Acid
x = 0:0.05:1;
u = 0.3;

n1 = area(x, (x+0.2)); % Neut1
hold on
n2 = area(x, (x-0.2)); %Neut2
m = area(x,-x-log(u));

set(gca,'Color',[1 1 1 ]);
%99.22% red, 91.76% green and 79.61% blue
n1.FaceColor = [0.99 0.91 0.79];
n2.FaceColor = [1 1 1];
m.FaceColor = [1 1 1];

n1_line = plot(x, (x+0.2)); %Neut
n2_line = plot(x, (x-0.2)); %Neut
m_line = plot(x,-x-log(u)); %Mixbed

l = legend([n1_line, n2_line, m_line],'Neut_1','Neut_2', 'Mixbed');
set(l,'color','w')

xlabel('base'),ylabel('acid');
% ylim([0 1])
% xlim([0 1])
c = linspace(0,1,length(neut(:,1)));

scatter(neut(:,1),neut(:,2),c);
scatter(mixbed(:,1),mixbed(:,2),c);
