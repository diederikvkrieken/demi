clear all
%filename = 'C:\Users\Diederik\IdeaProjects\demi\result\output.csv';
filename = 'C:\Users\Diederik\IdeaProjects\demi\result\reactive 0.05 to 0.6\output_1.csv';
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
%NASH SOLUTION
x = 0:0.005:1;
u = 0.4;
ma =0;
s = 0;
for Acid = x
    for Base = x
        for Water =x
            a = exp(-Water+Base)./exp(1);
            c = exp(-Water+Acid)./exp(1);
            m = exp(Water+Acid+Base)./exp(3);
            n = exp(-Acid-Base);
            if (a>u && c>u && m>u && n>u)
                s = a +c +m +n;
            end
            if (s>ma)
                ma = s;
                ac = Acid;
                ba = Base;
                wa = Water;
            end
        end
    end
end
nash = scatter3(ac,ba,wa, 'filled')
%hold off
%%
scatter3(anion(1,1),anion(1,2),anion(1,3));%, [], c, 'filled')
hold on
for i =2:1:length(anion)
    scatter3(anion(i,1),anion(i,2),anion(i,3));%, [], c, 'filled')
    if(waitforbuttonpress)
    end
end

%%
filename = 'C:\Users\Diederik\IdeaProjects\demi\result\output_distance.csv';
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

distance = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;
plot(distance)

%%
filename = 'C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbedrea\output_4_concession.csv';
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

concession = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;
anion = concession(1:4:end);
cation = concession(2:4:end);
mixbed = concession(3:4:end);
neut = concession(4:4:end);
plot(anion)
hold on
plot(cation)
plot(mixbed)
plot(neut)
hold off