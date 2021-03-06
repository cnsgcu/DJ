#  :sparkles: Install Notebook  :sparkles:

## :penguin: Ubuntu
### 1. Install Python from [source](https://www.python.org/downloads/) (Optional)
Install dependencies
```shell
$ sudo apt-get install build-essential libssl-dev libfreetype6-dev libsqlite3-dev libatlas-base-dev gfortran
```
Build and install Python
```shell
$ ./configure
$ make
$ sudo make altinstall
```

### 2. Install IPython notebook, numpy, scipy, matplotlib, pandas, scikit-learn, nltk
```shell
$ mkdir .pyvenv
$ pyvenv .pyvenv/IPython

$ source .pyvenv/IPython/bin/activate

(IPython) $ pip install ipython[all]
(IPython) $ pip install numpy
(IPython) $ pip install scipy
(IPython) $ pip install matplotlib
(IPython) $ pip install pandas
(IPython) $ pip install scikit-learn
(IPython) $ pip install nltk
(IPython) $ pip install scapy-python3 # brew install libdnet on Mac OS
```
