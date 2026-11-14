type HeaderProps = {
    onAddEndpoint: () => void
}

function Header({ onAddEndpoint }: HeaderProps) {
    return (
        <header className="h-20 border-b border-slate-800 flex items-center justify-between px-8">
            <div>
                <h2 className="text-xl font-semibold text-white">
                    Overview
                </h2>
                <p className="text-sm text-slate-400">
                    Monitor the health of your endpoints.
                </p>
            </div>

            <button
                onClick={onAddEndpoint}
                className="bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-medium px-4 py-2 rounded-lg transition-colors"
            >
                + Add Endpoint
            </button>
        </header>
    )
}

export default Header