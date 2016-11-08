x = 0:0.05:1;
u = 0.3;
% x = acid
% y = base
% z = water
[X,Y] = meshgrid(x);
[~,Z] = meshgrid(x);
%Anion: ln(u)+1 = Y - Z
ZA = Y - (log(u)+1);
%Cation: ln(u)+1 = X - Z
ZC = X- (log(u)+1);
%Mixbed: ln(u)+3 = X + Y + Z
ZM = 0.5*(-X -Y +log(u)+4);
%Neut: ln(u) = -X - Y
YN = -X - log(u);
%surf(X,Y,ZA)
hold on
%surf(X,Y,ZC)
surf(X,Y,ZM)
surf(X,YN,Z)
xlim([0,1])
ylim([0,1])
zlim([0,1])
xlabel('acid')
ylabel('base')
zlabel('water')
%%
clear all
x = 0:0.05:1;
u = 0.3;
% [acid, base, water] = meshgrid(x);
% anion = exp(-water+base)./exp(1);
% cation = exp(-water+acid)./exp(1);
% mixbed = exp(water+acid+base)./exp(3);
% neut = exp(-acid-base);

%if larger than minimum utility!
ma =0;
s = 0;
count = 0;
for i = 1:1:21
    ma =0;
    s = 0;
    for Acid = x
        for Base = x
            for Water =x
                a = exp(-Water+Base)/exp(1);
                c = exp(-Water+Acid)/exp(1);
                m = exp(Water+Acid+Base)/exp(3);
                n = exp(-Acid-Base);
                u = x(i);
                if (a>u && c>u && m>u && n>u)
                    s = a +c +m +n;
                else
                    ma(i) = (0/0);
                end
                if (s>ma)
                    ma(i) = s;
                    ac(i) = Acid;
                    ba(i) = Base;
                    wa(i) = Water;
                end
            end
        end
    end
end
scatter3(ac,ba,wa)

%%

anion(anion<u) = 0;
cation(cation<u) = 0;
mixebd(mixbed<u) = 0;
neut(neut<u) = 0;
sum = anion+cation+mixbed+neut;
[A, I] = max(sum(:));

[X, Y, Z] = ind2sub(size(sum),I);
neut(X,Y,Z)
%%
M = randn(10,10,10,10);
[C,I] = max(M(:));
C
M(I)
%(This might be useful too:

[I1,I2,I3,I4] = ind2sub(size(M),I);
M(I1,I2,I3,I4)



%%
[x,z] =meshgrid(-10:10, -10:10);
y=5-x;
surf(x,y,z); % Or some other 3D plot.
