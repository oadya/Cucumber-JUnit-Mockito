# lancement des tests

## lancer le frontend qformation-web et qformation-api à la main (ou automatiser le lancement avec maven pour du dev local)

	Voir ./qformation-web/readme.md
	Voir ./qformation-api/readme.md

    mvn verify -DTEST_IHM=true -Denforcer.skip=true -DSELENIUM_FRONT_URL=http://localhost:80 [-DSELENIUM_HUB_URL=https://monhubselenium] [-DSELENIUM_BROWSER=chrome]