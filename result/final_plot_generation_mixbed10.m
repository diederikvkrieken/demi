clear all
reactive_distance = cell(13);
load('C:\Users\Diederik\IdeaProjects\demi\result\nashproduct.mat')
nash = nash_product;
load('C:\Users\Diederik\IdeaProjects\demi\result\nashproduct_mixbed2.mat')
nash2 = nash_product_mixbed2;
load('C:\Users\Diederik\IdeaProjects\demi\result\nashproduct_mixbed10.mat')
reservation = [0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65];
nash10 = nash_product_mixbed10;
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


filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbed10\output_',int2str(i),'.csv');
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
nonreactive_mixbed10(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];

filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\reactive 0.05 to 0.6 mixbed10\output_',int2str(i),'.csv');
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
reactive_mixbed10(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];

filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\reactive 0.05 to 0.6 mixbed10\output_',int2str(i),'_distance.csv');
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

reactive_distance_mixbed10{i} = str2double(dataArray{1});

clearvars filename delimiter formatSpec fileID dataArray ans;
filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbed10\output_',int2str(i),'_distance.csv');
delimiter = '';
fileID = fopen(filename);

formatSpec = '%q%[^\n\r]';
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);

fclose(fileID);
fclose all;

nonreactive_distance_mixbed10{i} = str2double(dataArray{1});
clearvars filename delimiter formatSpec fileID dataArray ans;

na = [nash(i,1),nash(i,2),nash(i,3)];
na2 = [nash2(i,1),nash2(i,2),nash2(i,3)];
average_rea = [mean(reactive(i,:,1)),mean(reactive(i,:,2)),mean(reactive(i,:,3))];
average_nonrea = [mean(nonreactive(i,:,1)),mean(nonreactive(i,:,2)),mean(nonreactive(i,:,3))];
average_rea_mixbed10 = [mean(reactive_mixbed10(i,:,1)),mean(reactive_mixbed10(i,:,2)),mean(reactive_mixbed10(i,:,3))];
average_nonrea_mixbed10 = [mean(nonreactive_mixbed10(i,:,1)),mean(nonreactive_mixbed10(i,:,2)),mean(nonreactive_mixbed10(i,:,3))];
dist_rea(i) = pdist([na;average_rea]);
dist_nonrea(i) = pdist([na;average_nonrea]);
dist_rea_mixbed10(i) = pdist([na2;average_rea_mixbed10]);
dist_nonrea_mixbed10(i) = pdist([na2;average_nonrea_mixbed10]);


end
%%
figure
%plot(reservation, dist_rea, '--')
hold on
%plot(reservation, dist_nonrea)
plot(reservation, dist_rea_mixbed10, '-.')
hold on
plot(reservation, dist_nonrea_mixbed10, ':')
%legend('reactive','non reactive', 'reactive with mixbed water 10', 'nonreactive with mixbed water 10')
legend('reactive with mixbed water 10', 'nonreactive with mixbed water 10')
xlabel('reservation curve value')
ylabel('distance from nash solution')

%%
for i=1:13
[n(i,1),~] = size(reactive_distance_mixbed10{i}); 
[n(i,2),~] = size(nonreactive_distance_mixbed10{i}); 
m(i,1) = min(reactive_distance_mixbed10{i}(:));
m(i,2) = min(nonreactive_distance_mixbed10{i}(:));
end
tabel10 = [reservation; m(:,1)';n(:,1)';m(:,2)';n(:,2)'];
tabel10 = tabel10';

%%
reservation = [0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65];

for i = 1:1:13
x = 0:0.005:1;
u = reservation(i);
ma =0;
s = 0;
for Acid = x
    for Base = x
        for Water =x
            a = exp(-Water+Base)./exp(1);
            c = exp(-Water+Acid)./exp(1);
            m = exp((10*Water)+Acid+Base)./exp(12);
            n = exp(-Acid-Base);
            if (a>u && c>u && m>u && n>u)
                s = a *c *m *n;
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
if ma ==0
ma = 0;
ac = 0;
ba = 0;
wa = 0;
end
nash_product_mixbed10(i,:) = [ac, ba, wa, ma];
end