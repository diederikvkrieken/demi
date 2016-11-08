clear all
load('C:\Users\Diederik\IdeaProjects\demi\result\nashproduct_mixbed2.mat')
nash = nash_product_mixbed2;
reservation = [0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65];
for i = 1:1:13
filename = strcat('C:\Users\Diederik\IdeaProjects\demi\result\nonreactive 0.05 to 0.6 mixbed2\output_',int2str(i),'.csv');
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
[maximumnash, ~]= size(nash);
if i >maximumnash
x = 0:0.005:1;
u = reservation(i);
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
if ma ==0
ma = 0;
ac = 0;
ba = 0;
wa = 0;
end
nash(i,:) = [ac, ba, wa, ma];
end
nonreactive(i,:,:) = [anion(end,:);cation(end,:);mixbed(end,:);neut(end,:)];

end
%%
clear all
load('nonreactive.mat')
load('nonreactive_mixbed2.mat')
%load('nash.mat')
load('nashproduct.mat')
load('nashproduct_mixbed2.mat')
load('reactive.mat')
load('reactive_mixbed2.mat')
reservation = [0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65];
for i = 1:13
na = [nash_product(i,1),nash_product(i,2),nash_product(i,3)];
na_mb2 = [nash_product_mixbed2(i,1),nash_product_mixbed2(i,2),nash_product_mixbed2(i,3)];
average_rea = [mean(reactive(i,:,1)),mean(reactive(i,:,2)),mean(reactive(i,:,3))];
average_nonrea = [mean(nonreactive(i,:,1)),mean(nonreactive(i,:,2)),mean(nonreactive(i,:,3))];
average_reamb2 = [mean(reactive_mixbed2(i,:,1)),mean(reactive_mixbed2(i,:,2)),mean(reactive_mixbed2(i,:,3))];
average_nonreamb2 = [mean(nonreactive_mixbed2(i,:,1)),mean(nonreactive_mixbed2(i,:,2)),mean(nonreactive_mixbed2(i,:,3))];
%scatter3(average(1),average(2),average(3))
dist_rea(i) = pdist([na;average_rea]);
dist_nonrea(i) = pdist([na;average_nonrea]);
dist_reamb2(i) = pdist([na_mb2;average_reamb2]);
dist_nonreamb2(i) = pdist([na_mb2;average_nonreamb2]);
end
plot(reservation, dist_rea, '--')
hold on
plot(reservation, dist_nonrea)
plot(reservation,dist_reamb2, '--')
plot(reservation,dist_nonreamb2)
legend('reactive','non reactive', 'reactive mixbed 2', 'nonreactive mixbed2')
xlabel('reservation curve value')
ylabel('distance from nash solution')
%%

last = reactive;
for i = 1:1:13
scatter3(last(i,:,1),last(i,:,2),last(i,:,3), 'filled');%, [], c, 'filled')
xlim([0 1])
ylim([0 1])
zlim([0 1])
hold on
%scatter3(nash(i,1),nash(i,2),nash(i,3), 'filled');%, [], c, 'filled')
na = [nash(i,1),nash(i,2),nash(i,3)];
average = [mean(last(i,:,1)),mean(last(i,:,2)),mean(last(i,:,3))];
%scatter3(average(1),average(2),average(3))
test2(i) = pdist([na;average]);
%k = waitforbuttonpress;
end
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
                m = exp(Water+Acid+Base)./exp(3);
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
    nash_product(i,:) = [ac, ba, wa, ma];
end

%%
%%%%%%%%%% NASH SOLUTION WHERE MIXBED WATER IS *2%%%%%%%%%%%%%%
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
                m = exp((2*Water)+Acid+Base)./exp(4);
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
    nash_product(i,:) = [ac, ba, wa, ma];
end