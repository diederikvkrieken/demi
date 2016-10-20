filename = 'C:\Users\Diederik\IdeaProjects\demi\result\output.csv';
%test = csvread(filename);
delimiter = ';';
fileID = fopen(filename,'r');

%startRow = 2;
%formatSpec = '%f%f%f%f%[^\n\r]';
%dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter, 'EmptyValue' ,NaN,'HeaderLines' ,startRow-1, 'ReturnOnError', false);
formatSpec = '%q%q%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
acid = dataArray{:, 1};
base = dataArray{:, 2};
water = dataArray{:, 3};
%agent = dataArray{:, 4};
clearvars filename delimiter startRow formatSpec  fileID ans;
test = [acid, base, water];
anion = test(1:4:end,:);
%anion = test(test(:,4)==1,[1,2,3]);
cation = test(test(:,4)==2,[1,2,3]);
mixbed = test(test(:,4)==3,[1,2,3]);
neut = test(test(:,4)==4,[1,2,3]);
scatter3(anion(:,1),anion(:,2),anion(:,3))
hold on
scatter3(cation(:,1),cation(:,2),cation(:,3))
scatter3(mixbed(:,1),mixbed(:,2),mixbed(:,3))
scatter3(neut(:,1),neut(:,2),neut(:,3))
