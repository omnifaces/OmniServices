# OmniServices 

[![Build Status](https://travis-ci.org/omnifaces/omniservices.svg?branch=develop)](https://travis-ci.org/omnifaces/omniservices)
[![Maven](https://maven-badges.herokuapp.com/maven-central/org.omnifaces/omniservices/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.omnifaces/omniservices)
[![Javadoc](http://javadoc.io/badge/org.omnifaces/omniservices.svg)](http://javadoc.io/doc/org.omnifaces/omniservices) 
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Utility library that provides EJB3-like features for CDI beans

This project will attempt to implement CDI and Interceptor based versions of various EJB features and services. Currently the following features are implemented:

* EJB @Asynchronous - CDI based [@Asynchronous](https://www.javadoc.io/static/org.omnifaces/omniservices/0.4/org.omnifaces.services/org/omnifaces/services/asynchronous/Asynchronous.html) (note: this is currently available in native Jakarta EE via Jakarta Concurrency 3.0)
* EJB @Stateless - CDI based [@Pooled](https://github.com/omnifaces/omniservices/blob/develop/src/main/java/org/omnifaces/services/pooled/Pooled.java)+@Transactional or [@Service](https://github.com/omnifaces/omniservices/blob/develop/src/main/java/org/omnifaces/services/Service.java)
* EJB @Lock - CDI based [@Lock](https://github.com/omnifaces/omniservices/blob/develop/src/main/java/org/omnifaces/services/lock/Lock.java)

The following features were being considered for future versions:

* EJB @Schedule - this is currently available in native Jakarta EE 11 via Jakarta Concurrency 3.1
