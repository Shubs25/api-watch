import type { Endpoint } from '../type/endpoint'

// type Endpoint = {
//     id: string
//     name: string
//     url: string
//     method: string
//     status: 'UP' | 'DOWN'
//     responseTime: number | null
//     lastChecked: string
// }

type EndpointTableProps = {
    endpoints: Endpoint[]
    onCheck: (endpointId: string) => Promise<void>
}

const mockEndpoints: Endpoint[] = [
    {
        id: '1',
        name: 'Payment API',
        url: 'https://api.example.com/payments',
        method: 'GET',
        status: 'UP',
        responseTime: 124,
        lastChecked: 'Just now',
    },
    {
        id: '2',
        name: 'User Service',
        url: 'https://users.example.com',
        method: 'GET',
        status: 'DOWN',
        responseTime: null,
        lastChecked: '30 seconds ago',
    },
    {
        id: '3',
        name: 'Authentication API',
        url: 'https://auth.example.com',
        method: 'GET',
        status: 'UP',
        responseTime: 86,
        lastChecked: 'Just now',
    },
]

function EndpointTable({ endpoints, onCheck }: EndpointTableProps) {
    return (
        <div className="mt-10 bg-slate-900 border border-slate-800 rounded-xl overflow-hidden">

            <div className="p-6 border-b border-slate-800">
                <h3 className="text-lg font-semibold text-white">
                    Monitored Endpoints
                </h3>
                <p className="text-sm text-slate-400 mt-1">
                    Current health of your registered endpoints.
                </p>
            </div>

            <div className="overflow-x-auto">
                <table className="w-full text-left">
                    <thead className="bg-slate-800/40 text-xs uppercase text-slate-500">
                        <tr>
                            <th className="px-6 py-4 font-medium">Endpoint</th>
                            <th className="px-6 py-4 font-medium">Method</th>
                            <th className="px-6 py-4 font-medium">Status</th>
                            <th className="px-6 py-4 font-medium">Response Time</th>
                            <th className="px-6 py-4 font-medium">Last Checked</th>
                            <th className="px-6 py-4 font-medium">Active/Inactive</th>
                            <th className="px-6 py-4 font-medium">Action</th>
                        </tr>
                    </thead>

                    <tbody className="divide-y divide-slate-800">
                        {endpoints.map((endpoint) => (
                            <tr key={endpoint.id} className="hover:bg-slate-800/30 transition-colors">

                                <td className="px-6 py-4">
                                    <p className="text-sm font-medium text-white">
                                        {endpoint.name}
                                    </p>
                                    <p className="text-xs text-slate-500 mt-1">
                                        {endpoint.url}
                                    </p>
                                </td>

                                <td className="px-6 py-4">
                                    <span className="text-xs font-medium text-slate-300 bg-slate-800 px-2 py-1 rounded">
                                        {endpoint.method}
                                    </span>
                                </td>

                                <td className="px-6 py-4">
                                    <span
                                        className={`text-xs font-medium px-2.5 py-1 rounded-full ${endpoint.status === 'UP'
                                            ? 'text-emerald-400 bg-emerald-500/10'
                                            : 'text-rose-400 bg-rose-500/10'
                                            }`}
                                    >
                                        {endpoint.status}
                                    </span>
                                </td>

                                <td className="px-6 py-4 text-sm text-slate-300">
                                    {endpoint.responseTime !== null
                                        ? `${endpoint.responseTime} ms`
                                        : '--'}
                                </td>

                                <td className="px-6 py-4 text-sm text-slate-400">
                                    {endpoint.lastChecked}
                                </td>

                                <td className="px-6 py-4 text-sm text-slate-400">
                                    <span
                                        className={`px-2.5 py-1 rounded-full text-xs font-medium ${endpoint.active
                                            ? 'bg-emerald-500/10 text-emerald-400'
                                            : 'bg-slate-700/50 text-slate-400'
                                            }`}
                                    >
                                        {endpoint.active ? 'Active' : 'Inactive'}
                                    </span>
                                </td>

                                <td className="px-6 py-4 text-sm text-slate-400">
                                    <button
                                        onClick={() => onCheck(endpoint.id)}
                                        disabled={!endpoint.active}
                                        className="px-3 py-1.5 rounded-lg text-xs font-medium
    bg-indigo-500/10 text-indigo-400
    hover:bg-indigo-500/20
    disabled:opacity-40 disabled:cursor-not-allowed"
                                    >
                                        Check Now
                                    </button>
                                </td>

                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default EndpointTable