x = 0:0.05:1;
u = 0.5;
% x = acid
% y = base
% z = water
[X,Y] = meshgrid(x);
%Anion: ln(u)+1 = Y - Z
ZA = Y - (log(u)+1);
%Cation: ln(u)+1 = X - Z
ZC = X- (log(u)+1);
%Mixbed: ln(u)+3 = X + Y + Z
ZM = -X -Y +log(u)+3;
%Neut: ln(u) = -X - Y
YN = -X - log(u);
surf(X,Y,ZA)
hold on
surf(X,Y,ZC)
surf(X,Y,ZM)
surf(X,YN,zeros(21,21))



