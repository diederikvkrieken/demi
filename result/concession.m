filename = 'C:\Users\Diederik\IdeaProjects\demi\result\reactive test\output_4.csv';
delimiter = ',';
fileID = fopen(filename);

formatSpec = '%q%q%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

acid = str2double(dataArray{1});
base = str2double(dataArray{2});
water = str2double(dataArray{3});
total = [acid base water];

clearvars filename delimiter formatSpec fileID dataArray ans;
clearvars acid base water;


anion = total(1:4:end,:);
cation = total(2:4:end,:);
mixbed = total(3:4:end,:);
neut = total(4:4:end,:);
%c = linspace(1,10,length(anion));
%%
figure
a = scatter3(anion(:,1),anion(:,2),anion(:,3));%, [], c, 'filled')
hold on
b=scatter3(cation(:,1),cation(:,2),cation(:,3), 'filled');%, [], c, 'filled')
m = scatter3(mixbed(:,1),mixbed(:,2),mixbed(:,3), '*');%, [], c, 'filled')
n=scatter3(neut(:,1),neut(:,2),neut(:,3), '+');%, [], c, 'filled')
l = legend([a, b, m, n],'anion','cation', 'mixbed','neut');
xlabel('acid')
ylabel('base')
zlabel('water')
xlim([0 1])
ylim([0 1])
zlim([0 1])
last = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];
scatter3(last(:,1),last(:,2),last(:,3), 'filled');%, [], c, 'filled')

%%
filename = 'C:\Users\Diederik\IdeaProjects\demi\result\reactive test\output_4_concession.csv';
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

concession = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;
anion_con = concession(4:4:end);
cation_con = concession(1:4:end);
mixbed_con = concession(2:4:end);
neut_con = concession(3:4:end);
last = 12
figure
plot(anion_con(1:last))
hold on
plot(cation_con(1:last))
plot(mixbed_con(1:last))
plot(neut_con(1:last))
legend('anion','cation', 'mixbed','neut');

hold off

%%

filename = 'C:\Users\Diederik\IdeaProjects\demi\result\reactive test\output_4_distance_avg.csv';
delimiter = ',';
fileID = fopen(filename);

formatSpec = '%q%q%q%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);
fclose(fileID);
fclose all;
figure
anion_avg = str2double(dataArray{1});
cation_avg = str2double(dataArray{2});
mixbed_avg = str2double(dataArray{3});
neut_avg = str2double(dataArray{4});
total = [anion_avg cation_avg mixbed_avg neut_avg];
clearvars filename delimiter formatSpec fileID dataArray ans;



%%
last = 50;
plot(anion_avg(1:last))
hold on
plot(cation_avg(1:last))
plot(mixbed_avg(1:last))
plot(neut_avg(1:last))
hold off
%%
%figure
round = cation_avg>0.9;
round = [zeros(50,1); round(51:110)];
round = logical(round==1);
t = scatter3(cation(round,1),cation(round,2),cation(round,3), 'filled');%, [], c, 'filled')
hold on
b=scatter3(cation(1:last,1),cation(1:last,2),cation(1:last,3));%, [], c, 'filled')
%t = scatter3(cation(round,1),cation(round,2),cation(round,3), 'filled');%, [], c, 'filled')
%%
b=scatter3(cation(:,1),cation(:,2),cation(:,3), 'filled');%, [], c, 'filled')
hold on
t = scatter3(cation(round,1),cation(round,2),cation(round,3), 'filled');%, [], c, 'filled')
%%

filename = 'C:\Users\Diederik\IdeaProjects\demi\result\reactive test\output_4_desire.csv';
delimiter = ',';
fileID = fopen(filename);

formatSpec = '%q%q%q%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);
fclose(fileID);
fclose all;
figure
anion_des = str2double(dataArray{1});
cation_des = str2double(dataArray{2});
mixbed_des = str2double(dataArray{3});
neut_des = str2double(dataArray{4});
total = [anion_des cation_des mixbed_des neut_des];
clearvars filename delimiter formatSpec fileID dataArray ans;

last = length(anion_des);
plot(anion_des(1:last))
hold on
plot(cation_des(1:last))
plot(mixbed_des(1:last))
plot(neut_des(1:last))
xlabel('round')
ylabel('utility desired')
legend('Anion u(t)','Cation u(t)','Mixbed u(t)','Neut u(t)')
ylim([0 1])
hold off