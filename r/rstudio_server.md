# :sparkles: Install RStudio Server :sparkles:
## :penguin: Ubuntu
1. Choose a CRAN mirror from https://cran.r-project.org/mirrors.html to add a deb entry in /etc/apt/sources.list file
  
  ```shell
  $ deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu wily/
  ```
  or
  ```shell
  $ deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu vivid/
  ```
  or
  ```shell
  $ deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu trusty/
  ```
  or
  ```shell
  $ deb https://<my.favorite.cran.mirror>/bin/linux/ubuntu precise/
  ```
2. Add the signed key from [CRAN](https://cran.rstudio.com/bin/linux/ubuntu/README.html)

  ```shell
  $ sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9
  ```
  
3. Install R

   ```shell
   $ sudo apt-get install r-base
   ```
   
4. Install the latest RStudio Server

  64 bit
  ```shell
  $ wget https://download2.rstudio.org/rstudio-server-0.99.491-amd64.deb
  $ dpkg -I rstudio-server-0.99.491-amd64.deb  # list required dependencies to install (Optional)
  
  $ sudo dpkg -i rstudio-server-0.99.491-amd64.deb # install RStudio Server 64 bit
  ```
  32 bit
  ```shell
  $ wget https://download2.rstudio.org/rstudio-server-0.99.491-i386.deb
  $ dpkg -I rstudio-server-0.99.491-i386.deb  # list required dependencies to install (Optional)
  
  $ sudo dpkg -i rstudio-server-0.99.491-i386.deb # install RStudio Server 32 bit
  ```
  
