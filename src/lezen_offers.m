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
scatter3(anion(:,1),anion(:,2),anion(:,3))
hold on
scatter3(cation(:,1),cation(:,2),cation(:,3))
scatter3(mixbed(:,1),mixbed(:,2),mixbed(:,3))
scatter3(neut(:,1),neut(:,2),neut(:,3))
