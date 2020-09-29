

npm-libs:
	npm install @react-native-community/async-storage


npx-pod:
	npx pod-install


.PHONY: joker
joker:
	find src -name "*.cljs" -exec joker --lint {} \;
