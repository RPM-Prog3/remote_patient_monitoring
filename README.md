# Remote Patient Monitoring

<p align="center">
	<img src="https://travis-ci.com/Joearrowsmith/remote_patient_monitoring.svg?branch=master"
     		alt="Build Status">
	<img src="https://img.shields.io/badge/Java-1.8.0__172-orange"
     		alt="Java Version">
	<img src="https://img.shields.io/github/repo-size/joearrowsmith/remote_patient_monitoring"
     		alt="Github Repo Size">
</p>


Real-time patient visualisation of vital signs with a simple easy to read UI.

### Vitals requirements:

- [ ] Body temperature
  - [ ] Current temperature
  - [x] Normal
  - [ ] Abnormal
- [ ] Respiratory rate
  - [ ] Current rate
  - [x] Normal
  - [ ] Abnormal
- [ ] Blood pressure
  - [ ] Max & min pressure
  - [x] Normal
  - [ ] Abnormal
- [ ] ECG
  - [x] BPM (HR)
  - [x] Normal
  - [ ] Abnormal

### Feature requirements:

- [x] A display of each vital sign plotted over the last n records, where n is selectable and display of ECG
- [ ] Visual alarms that indicate if the values are outside normal value
  - [ ] Warning
  - [ ] Urgent
- [ ] A permanent record (database) to be kept of average values for every minute during a day and also instances when there have been abnormal signs
- [ ] A tunable App.simulation of the vital signs and ECG for the system to display:
  - [ ] Simulate a normal patient
  - [ ] Simulate a patient who has abnormal vital signs
  
  
### Feature desirables:

- [ ] The ability to monitor multiple patients at the same time - records to be kept of each patient's identity details
- [ ] Audio and email alarms
- [ ] Audio indication of heart beat
- [ ] A report generated of the average values recorded during a day, and when any abnormal signs happened

### Initial Feedback Suggestions:

- [ ] Add server to be able to remotely monitor patients.

---

## Prototypes:

### Simulation Branch:

![Simulation Working Document](misc/simulation_dev_summary.jpg)

### Patients Log Management:

![Patient Management Database](misc/patients_log_management.jpg)


### Examples from Industry:

![Simulation Working Document](misc/ExampleOfGui_1.jpg)

![Simulation Working Document](misc/ExampleOfGui_2.jpg)

![Simulation Working Document](misc/ExampleOfGui_3.jpg)

### Our Progress:

#### First GUI:

![First GUI version](misc/first_gui.jpg)

#### GUI with custom signals:

![GUI with signals version](misc/gui_with_custom_signals.png)
