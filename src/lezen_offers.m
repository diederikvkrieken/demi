clear all
hold off
filename = 'C:\Users\Diederik\IdeaProjects\demi\result\output.csv';
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
b=scatter3(cation(:,1),cation(:,2),cation(:,3));%, [], c, 'filled')
m = scatter3(mixbed(:,1),mixbed(:,2),mixbed(:,3));%, [], c, 'filled')
n=scatter3(neut(:,1),neut(:,2),neut(:,3));%, [], c, 'filled')
l = legend([a, b, m, n],'anion','cation', 'neut', 'mixbed');
xlabel('acid')
ylabel('base')
zlabel('water')

%%
scatter3(anion(1,1),anion(1,2),anion(1,3));%, [], c, 'filled')
hold on
for i =2:1:length(anion)
    scatter3(anion(i,1),anion(i,2),anion(i,3));%, [], c, 'filled')
    if(waitforbuttonpress)
    end
end

