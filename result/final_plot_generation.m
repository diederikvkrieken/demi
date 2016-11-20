%clear all
reactive_distance = cell(13,1);
load('C:\Users\Diederik\IdeaProjects\demi\result\nashproduct.mat')
nash = nash_product;
reservation = [0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65];

for i = 1:1:13
filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\reactive 0.05 to 0.6\output_',int2str(i),'.csv');
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
reactive(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];

filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6\output_',int2str(i),'.csv');
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
nonreactive(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];


filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbedrea\output_',int2str(i),'.csv');
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
nonreactive_mixbedrea(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];

filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\reactive 0.05 to 0.6\output_',int2str(i),'_distance.csv');
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

reactive_distance{i} = str2double(dataArray{1});

clearvars filename delimiter formatSpec fileID dataArray ans;
filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6\output_',int2str(i),'_distance.csv');
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

nonreactive_distance{i} = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;
filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbedrea\output_',int2str(i),'_distance.csv');
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

nonreactive_mixbedrea_distance{i} = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;

na = [nash_product(i,1),nash_product(i,2),nash_product(i,3)];
average_rea = [mean(reactive(i,:,1)),mean(reactive(i,:,2)),mean(reactive(i,:,3))];
average_nonrea = [mean(nonreactive(i,:,1)),mean(nonreactive(i,:,2)),mean(nonreactive(i,:,3))];
average_nonrea_mixbedrea = [mean(nonreactive_mixbedrea(i,:,1)),mean(nonreactive_mixbedrea(i,:,2)),mean(nonreactive_mixbedrea(i,:,3))];
dist_rea(i) = pdist([na;average_rea]);
dist_nonrea(i) = pdist([na;average_nonrea]);
dist_nonrea_mixbedrea(i) = pdist([na;average_nonrea_mixbedrea]);

end

%%
figure
plot(reservation, dist_rea, '--')
hold on
plot(reservation, dist_nonrea)
plot(reservation, dist_nonrea_mixbedrea, '-.g')
legend('reactive','non reactive', 'nonreactive with reactive mixbed')
xlabel('reservation curve value')
ylabel('distance from nash solution')

%%
figure

for i=1:13
[n(i,1),~] = size(reactive_distance{i}); 
[n(i,2),~] = size(nonreactive_distance{i}); 
[n(i,3),~] = size(nonreactive_mixbedrea_distance{i}); 
m(i,1) = min(reactive_distance{i}(:));
m(i,2) = min(nonreactive_distance{i}(:));
m(i,3) = min(nonreactive_mixbedrea_distance{i}(:));
plot(1:n(i),reactive_distance{i})
hold on

end

%%

%Write csv file

tabel = [reservation; m(:,1)';m(:,2)';n(:,1)';n(:,2)'];
tabel = tabel';
tabel3 = [reservation; m(:,3)';n(:,3)'];
tabel3 = tabel3';