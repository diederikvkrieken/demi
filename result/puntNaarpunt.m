function [ x, y ] = puntNaarpunt( a, b, c, x0, y0 )
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
        %//x = \frac{b(bx_0 - ay_0)-ac}{a^2 + b^2}
        x = (((b*((b*x0) - (a*y0)))-a*c)/(a^2+b^2));
        %//y = \frac{a(-bx_0 + ay_0) - bc}{a^2+b^2}.</math>
        y = (((a*(-(b*x0) + (a*y0)))-b*c)/(a^2+b^2));
end

