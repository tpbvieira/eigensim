Model order selection and eigen similarity based framework for detection and identification of network attacks
==============================================================================================================
-----

Eigensim is a framework based on eigenvalues and eigenvectors similarity for anomaly detection, with application on network attack detection. W

-----
**Citing Eigensim**\ :

`Eigensim paper <https://www.sciencedirect.com/science/article/abs/pii/S1084804517301601>`_ is published in
`Journal of Network and Computer Applications (JNCA) <https://www.sciencedirect.com/journal/journal-of-network-and-computer-applications/>`.
If you use Eigensim in a scientific publication, we would appreciate citations to the following paper::

	@article{VIEIRA201726,
	title = {Model order selection and eigen similarity based framework for detection and identification of network attacks},
	journal = {Journal of Network and Computer Applications},
	volume = {90},
	pages = {26-41},
	year = {2017},
	issn = {1084-8045},
	doi = {https://doi.org/10.1016/j.jnca.2017.04.012},
	url = {https://www.sciencedirect.com/science/article/pii/S1084804517301601},
	author = {Thiago P.B. Vieira and Danilo F. Tenório and João Paulo C.L. {da Costa} and Edison P. {de Freitas} and Giovanni Del Galdo and Rafael T. {de Sousa Júnior}},
	keywords = {Network attack detection, Model order selection, Eigen analysis, Similarity analysis}
	}

-----
Java install:
.. code-block:: shell
	mvn clean install
	mvn release:prepare release:perform -Dmaven.javadoc.skip=true
