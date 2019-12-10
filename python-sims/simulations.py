import numpy as np
import matplotlib.pyplot as plt

def get_step(pos, target_mean, sigma, adjustment_speed=0.5):
    step = np.random.normal(0, sigma)
    next_pos = pos + step
    diff = next_pos - target_mean 
    adjustment = np.sign(diff) * step # if negative moving to mean, else moving away from mean
    if adjustment > 0:
        step *= adjustment_speed
    return step

def body_temp():
    pos = input_mean = 37.0
    values = [pos]

    for i in range(5000):
        target_mean = 0.2 * np.sin(i%(30+np.random.normal(0.0, 1.0))) + input_mean

        step = get_step(pos, target_mean, 0.01)
        pos += step
        values.append(pos)
    plt.figure()
    plt.plot(values)

def resp():
    pos = input_mean = 100
    values = [pos]
    period = 20
    i = 0
    
    for idx in range(1000):
        i += 1 + np.absolute(np.random.normal(0,3.0))
        rperiod = period
        sin = np.sin(i / period)
        if sin < -0.95:
            i -= np.absolute(np.random.normal(0,3.0))
            sin = np.sin(i / period)
        
        target_mean = sin + input_mean
        step = get_step(pos, target_mean, 0.0)
        pos += step
        values.append(target_mean)
    plt.figure()
    plt.plot(values)
    

def blood_pressure(bp_range=[80,120]):
    i = 0
    values = []
    period = 100
    t1 = 0.60
    t2 = 0.15
    t3 = 0.25
    assert (t1 + t2 + t3) == 1
    for idx in range(300):
        i_period = i % period
        i += 1
        if i_period < (period * t1):
            values.append(-np.cos(i_period/14)*20 + 100) 
        elif i_period < (period * (t1+t2)):
            new_start = i_period - period * t1
            values.append(-np.sin(new_start/2)*1 + 109)
        else:
            new_start = i_period - period * (t1+t2)
            values.append(-np.sin(new_start/15)*27+107)
    plt.figure()
    plt.plot(values)
        


body_temp()
resp()
blood_pressure()
plt.show()