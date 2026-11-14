function Sidebar() {
    return (
        <aside className="w-64 h-screen shrink-0 bg-slate-900 border-r border-slate-800 p-6">
            {/* Brand */}
            <div className="mb-10">
                <h1 className="text-2xl font-bold text-white tracking-tight">
                    API<span className="text-emerald-400">Watch</span>
                </h1>
                <p className="text-xs text-slate-500 mt-1">
                    Endpoint monitoring
                </p>
            </div>

            {/* Navigation */}
            <nav className="space-y-2">
                <a
                    href="/"
                    className="block px-4 py-3 rounded-lg bg-slate-800 text-white font-medium"
                >
                    Overview
                </a>

                <a
                    href="/endpoints"
                    className="block px-4 py-3 rounded-lg text-slate-400 hover:bg-slate-800 hover:text-white transition-colors"
                >
                    Endpoints
                </a>
            </nav>
        </aside>
    )
}

export default Sidebar