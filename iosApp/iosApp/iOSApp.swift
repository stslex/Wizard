import SwiftUI

@main
struct iOSApp: App {

    init {
        KoinInitializerKt.init()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}