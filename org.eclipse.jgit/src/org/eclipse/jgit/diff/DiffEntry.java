/**
 * A value class representing a change to a file
 */
	 * @throws java.io.IOException
	 * @throws java.lang.IllegalArgumentException
	 * @throws java.io.IOException
	 * @throws java.lang.IllegalArgumentException
	 * @throws java.io.IOException
	 * @throws java.lang.IllegalArgumentException
		List<DiffEntry> r = new ArrayList<>();
	/**
	 * Get the old file mode
	 *
	 * @return the old file mode, if described in the patch
	 */
	/**
	 * Get the new file mode
	 *
	 * @return the new file mode, if described in the patch
	 */
	/**
	 * Get the change type
	 *
	 * @return the type of change this patch makes on {@link #getNewPath()}
	 */
	 * Get similarity score
	 *
	 *         {@link org.eclipse.jgit.diff.DiffEntry.ChangeType#COPY} or
	 *         {@link org.eclipse.jgit.diff.DiffEntry.ChangeType#RENAME}.
	 *            and {@link java.lang.Integer#SIZE}).
	 * @return a boolean.
	/** {@inheritDoc} */
}