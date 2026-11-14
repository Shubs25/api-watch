import { useState, type FormEvent } from 'react'

export type NewEndpoint = {
    name: string
    url: string
    method: string
    interval: number
    timeout: number
}

type AddEndpointModalProps = {
    onClose: () => void
    onSubmit: (endpoint: NewEndpoint) => Promise<void>
}

function AddEndpointModal({ onClose, onSubmit }: AddEndpointModalProps) {
    const [name, setName] = useState('')
    const [url, setUrl] = useState('')
    const [method, setMethod] = useState('GET')
    const [interval, setinterval] = useState(60)
    const [timeout, settimeout] = useState(5000)

    const [isSubmitting, setIsSubmitting] = useState(false)
    const [error, setError] = useState<string | null>(null)

    async function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        setError(null)
        setIsSubmitting(true)

        try {
            await onSubmit({
                name,
                url,
                method,
                interval,
                timeout,
            })
        } catch (err) {
            setError(
                err instanceof Error ? err.message : 'Something went wrong.'
            )
        } finally {
            setIsSubmitting(false)
        }
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/70 p-4">
            <div className="w-full max-w-lg rounded-xl border border-slate-700 bg-slate-900 shadow-2xl">

                <div className="flex items-center justify-between border-b border-slate-800 p-6">
                    <div>
                        <h2 className="text-xl font-semibold text-white">
                            Add Endpoint
                        </h2>
                        <p className="mt-1 text-sm text-slate-400">
                            Configure a new endpoint to monitor.
                        </p>
                    </div>

                    <button
                        type="button"
                        onClick={onClose}
                        className="text-slate-400 hover:text-white text-xl"
                        aria-label="Close modal"
                    >
                        ×
                    </button>
                </div>

                <form onSubmit={handleSubmit} className="space-y-5 p-6">

                    <div>
                        <label className="mb-2 block text-sm font-medium text-slate-300">
                            Endpoint Name
                        </label>
                        <input
                            type="text"
                            required
                            maxLength={100}
                            value={name}
                            onChange={(event) => setName(event.target.value)}
                            placeholder="e.g. Payment API"
                            className="w-full rounded-lg border border-slate-700 bg-slate-800 px-4 py-3 text-sm text-white placeholder:text-slate-500 outline-none focus:border-emerald-500"
                        />
                    </div>

                    <div>
                        <label className="mb-2 block text-sm font-medium text-slate-300">
                            URL
                        </label>
                        <input
                            type="url"
                            required
                            maxLength={200}
                            value={url}
                            onChange={(event) => setUrl(event.target.value)}
                            placeholder="https://api.example.com"
                            className="w-full rounded-lg border border-slate-700 bg-slate-800 px-4 py-3 text-sm text-white placeholder:text-slate-500 outline-none focus:border-emerald-500"
                        />
                    </div>

                    <div className="grid grid-cols-1 gap-5 sm:grid-cols-2">
                        <div>
                            <label className="mb-2 block text-sm font-medium text-slate-300">
                                HTTP Method
                            </label>
                            <select
                                value={method}
                                onChange={(event) => setMethod(event.target.value)}
                                className="w-full rounded-lg border border-slate-700 bg-slate-800 px-4 py-3 text-sm text-white outline-none focus:border-emerald-500"
                            >
                                <option value="GET">GET</option>
                                <option value="POST">POST</option>
                                <option value="PUT">PUT</option>
                                <option value="DELETE">DELETE</option>
                            </select>
                        </div>

                        <div>
                            <label className="mb-2 block text-sm font-medium text-slate-300">
                                Interval (seconds)
                            </label>
                            <input
                                type="number"
                                required
                                min={30}
                                max={300}
                                value={interval}
                                onChange={(event) => setinterval(Number(event.target.value))}
                                className="w-full rounded-lg border border-slate-700 bg-slate-800 px-4 py-3 text-sm text-white outline-none focus:border-emerald-500"
                            />
                        </div>
                    </div>

                    <div>
                        <label className="mb-2 block text-sm font-medium text-slate-300">
                            Timeout (milliseconds)
                        </label>
                        <input
                            type="number"
                            required
                            min={1}
                            max={120000}
                            value={timeout}
                            onChange={(event) => settimeout(Number(event.target.value))}
                            className="w-full rounded-lg border border-slate-700 bg-slate-800 px-4 py-3 text-sm text-white outline-none focus:border-emerald-500"
                        />
                        <p className="mt-1 text-xs text-slate-500">
                            Maximum time to wait for the endpoint to respond.
                        </p>
                    </div>

                    <div className="flex justify-end gap-3 border-t border-slate-800 pt-5">
                        <button
                            type="button"
                            onClick={onClose}
                            className="rounded-lg px-4 py-2 text-sm font-medium text-slate-300 hover:bg-slate-800"
                        >
                            Cancel
                        </button>

                        {error && (
                            <p className="text-sm text-rose-400" role="alert">
                                {error}
                            </p>
                        )}

                        <button
                            type="submit"
                            disabled={isSubmitting}
                            className="rounded-lg bg-emerald-500 px-4 py-2 text-sm font-semibold text-slate-950 hover:bg-emerald-400 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
                        >
                            {isSubmitting ? 'Adding...' : 'Add Endpoint'}
                        </button>
                    </div>

                </form>
            </div>
        </div>
    )
}

export default AddEndpointModal